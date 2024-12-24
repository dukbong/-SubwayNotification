package study.main_server.ttt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class jiTest {

    static class Person {
        private String name;
        private int age;

        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
    }

    static class Person2 implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private int age;

//        public Person2() {}

        public Person2(String name, int age) {
            this.name = name;
            this.age = age;
        }

//        public String getName(){
//            return name;
//        }
//        public int getAge(){
//            return age;
//        }
    }

    @Test
    void serializationTest1() throws JsonProcessingException {
        // given
        Person person = new Person("test1", 10);
        ObjectMapper mapper = new ObjectMapper();
        // when
        byte[] result = mapper.writeValueAsBytes(person);
        // then
        System.out.println(result);
    }

    @Test
    void serializationTest2() throws JsonProcessingException {
        // given
        Person person = new Person("test1", 10);
        ObjectMapper mapper = new ObjectMapper();
        // when
        String result = mapper.writeValueAsString(person);
        // then
        System.out.println(result);
    }

    @Test
    void serializationTest5() throws JsonProcessingException {
        // given
        Person person = new Person("test1", 10);
        ObjectMapper mapper = new ObjectMapper();
        // when
        String result = mapper.writeValueAsString(person);
        // then
        Person result2 = mapper.readValue(result, Person.class);

        assertThat(person.getName()).isEqualTo(result2.getName());
    }

    @Test
    void serializationTest3() throws IOException {
        // given
        Person2 person = new Person2("test1", 10);
        byte[] serializeResult = serialize(person);
        System.out.println(serializeResult);
    }

    @Test
    void serializationTest4() throws IOException, ClassNotFoundException {
        // given
        Person2 person = new Person2("test1", 10);
        byte[] serializeResult = serialize(person);
        Person2 DeserializeResult = deserialize(serializeResult, Person2.class);
        System.out.println(DeserializeResult);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return clazz.cast(objectInputStream.readObject());  // 객체로 역직렬화
    }


    /***
     * 결론
     * ObjectMapper 사용시 getter와 기본생성자 직렬화, 역직렬화 시 필수 (속도 느림, 호환성 굿)
     *
     * java 기본 직렬화, 역직렬화시 다 필요없음 (속도 빠름, 호환성 낮음)
     */
}
