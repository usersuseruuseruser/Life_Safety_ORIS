package proj.tcp;

public class ClientTest {
    public static void main(String[] args) {
        GreetingClient client = new GreetingClient();
        client.start("127.0.0.1",5555);

        System.out.println(client.send("hello"));
        System.out.println(client.send("ping"));
        System.out.println(client.send("bye"));
        System.out.println(client.send(">_>"));

        client.stop();
    }
}
