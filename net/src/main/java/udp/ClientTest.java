package udp;

public class ClientTest {
    public static void main(String[] args) {
        new GreetingServer().start();

        try(GreetingClient client = new GreetingClient()){
            System.out.println(client.send("123"));
            System.out.println(client.send("bye"));
            System.out.println(client.send("321"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
