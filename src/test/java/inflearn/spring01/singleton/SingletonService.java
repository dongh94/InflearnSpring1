package inflearn.spring01.singleton;

public class SingletonService {
    // 정리 : 하나 인스턴스 new 생성 후, 외부 조회 메서드 만든 후, private 생성자로 막는다.
    // 1. static 영역에 객체를 딱 1개만 생성.
    private static SingletonService instance = new SingletonService();
    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드로 getInstance로 조회 가능하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }
    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }
    // 4. 로직이 필요하면 내부에서만 싱글톤 객체의 로직을 만든다.
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
