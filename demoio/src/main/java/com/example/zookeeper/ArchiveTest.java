package com.example.zookeeper;

import org.apache.jute.*;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;

public class ArchiveTest {
    public static void main( String[] args ) throws IOException {
        String path = "D:\\test.txt";
        // write operation
        OutputStream outputStream = new FileOutputStream(new File(path));
        BinaryOutputArchive binaryOutputArchive = BinaryOutputArchive.getArchive(outputStream);

        binaryOutputArchive.writeBool(true, "boolean");
        byte[] bytes = "leesf".getBytes();
        binaryOutputArchive.writeBuffer(bytes, "buffer");
        binaryOutputArchive.writeDouble(13.14, "double");
        binaryOutputArchive.writeFloat(5.20f, "float");
        binaryOutputArchive.writeInt(Integer.MAX_VALUE, "int");
        Person person = new Person(25, "leesf");
        binaryOutputArchive.writeRecord(person, "leesf");
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        map.put("leesf", 25);
        map.put("dyd", 25);
        Set<String> keys = map.keySet();
        binaryOutputArchive.startMap(map, "map");
        int i = 0;
        for (String key: keys) {
            String tag = i + "";
            binaryOutputArchive.writeString(key, tag);
            binaryOutputArchive.writeInt(map.get(key), tag);
            i++;
        }

        binaryOutputArchive.endMap(map, "map");


        // read operation
        InputStream inputStream = new FileInputStream(new File(path));
        BinaryInputArchive binaryInputArchive = BinaryInputArchive.getArchive(inputStream);

        System.out.println(binaryInputArchive.readBool("boolean"));
        System.out.println(new String(binaryInputArchive.readBuffer("buffer")));
        System.out.println(binaryInputArchive.readDouble("double"));
        System.out.println(binaryInputArchive.readFloat("float"));
        System.out.println(binaryInputArchive.readInt("int"));
        Person person2 = new Person();
        binaryInputArchive.readRecord(person2, "leesf");
        System.out.println(person2);

        Index index = binaryInputArchive.startMap("map");
        int j = 0;
        while (!index.done()) {
            String tag = j + "";
            System.out.println("key = " + binaryInputArchive.readString(tag)
                    + ", value = " + binaryInputArchive.readInt(tag));
            index.incr();
            j++;
        }
    }

    static class Person implements Record {
        private int age;
        private String name;

        public Person() {

        }

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public void serialize(OutputArchive archive, String tag) throws IOException {
            archive.startRecord(this, tag);
            archive.writeInt(age, "age");
            archive.writeString(name, "name");
            archive.endRecord(this, tag);
        }

        public void deserialize(InputArchive archive, String tag) throws IOException {
            archive.startRecord(tag);
            age = archive.readInt("age");
            name = archive.readString("name");
            archive.endRecord(tag);
        }

        public String toString() {
            return "age = " + age + ", name = " + name;
        }
    }
}