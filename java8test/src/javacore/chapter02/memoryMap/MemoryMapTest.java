package javacore.chapter02.memoryMap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest {
    public static long checksumInputStream(Path filename) throws IOException {
        try(final InputStream inputStream = Files.newInputStream(filename)){
            CRC32 crc32 = new CRC32();
            int c;
            byte[] buffer = new byte[8192];
            while ((c = inputStream.read(buffer)) != -1) {
                crc32.update(buffer,0,c);
            }
            return crc32.getValue();
        }
    }

    public static long checksumBufferedInputStream(Path filename) throws IOException {
        try(final BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(filename),8192)){
            CRC32 crc32 = new CRC32();
            int c;
            while ((c = inputStream.read()) != -1) {
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }


    public static long checksumRandomAccessFile(Path filename) throws IOException {
        try(final RandomAccessFile inputStream = new RandomAccessFile(filename.toFile(),"r")){
            CRC32 crc32 = new CRC32();
            int c;
            byte[] buffer = new byte[8192];
            while ((c = inputStream.read(buffer)) != -1) {
                crc32.update(buffer,0,c);
            }
            return crc32.getValue();
        }
    }

    public static long checksumMappedFile(Path filename) throws IOException {
        try(final FileChannel channel = FileChannel.open(filename)){
            CRC32 crc32 = new CRC32();
            final MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            byte[] buffer = new byte[8192];
            long size = channel.size();

            while (map.hasRemaining()) {
                if (size > 8192) {
                     map.get(buffer);
                     crc32.update(buffer);
                    size = size-8192;
                }else{
                    map.get(buffer,0,(int)size);
                    crc32.update(buffer,0,(int)size);
                }

            }
            return crc32.getValue();
//            for (int i = 0; i < channel.size(); i++) {
//                int c = map.get();
//                crc32.update(c);
//            }
//            return crc32.getValue();
        }
    }

    public static void main(String[] args) throws Exception {
        final Path filename = Paths.get("d:\\soft\\jdk-9_doc-api-spec-biying.rar");
        System.out.println("input stream");
        long start = System.currentTimeMillis();
        long crc32 = checksumInputStream(filename);
        System.out.println(Long.toHexString(crc32));
        long end = System.currentTimeMillis();
        System.out.println((end - start)+" milliseconds");

        System.out.println("buffered input stream");
        start = System.currentTimeMillis();
        crc32 = checksumBufferedInputStream(filename);
        System.out.println(Long.toHexString(crc32));
        end = System.currentTimeMillis();
        System.out.println((end - start)+" milliseconds");

        System.out.println("randomAccess file");
        start = System.currentTimeMillis();
        crc32 = checksumRandomAccessFile(filename);
        System.out.println(Long.toHexString(crc32));
        end = System.currentTimeMillis();
        System.out.println((end - start)+" milliseconds");

        System.out.println("mapped file");
        start = System.currentTimeMillis();
        crc32 = checksumMappedFile(filename);
        System.out.println(Long.toHexString(crc32));
        end = System.currentTimeMillis();
        System.out.println((end - start)+" milliseconds");
    }
}
