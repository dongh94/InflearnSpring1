package inflearn.spring01.singleton;

public class StatefulService {
    // 상태를 유지하는 필드 == > 결국 무상태로 만들어야 한다.
    private int price;

    public int getPrice() {
        return price;
    }
    // 문제점 해결책 : return 하라.
    // price를 공유하지 말고 return해 받게 하여 무상태, 무결정을 유지한다.
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제 == > 상태 유지(공유 값)를 변화시키기 때문에
    }
}
