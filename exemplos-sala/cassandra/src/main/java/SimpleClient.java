import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class SimpleClient {

    private Cluster cluster;
    private Session session;

    public void connect(String node) {
        //  Conexão com Cluster
        cluster = Cluster.builder().addContactPoint(node).build();
        session = cluster.connect();
        //  Obtenção de metadados
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Conectado ao Clustar: " + metadata.getClusterName());

        for (Host host : metadata.getAllHosts()) {
            System.out.println(host.getDatacenter() + " " + host.getAddress() + " " + host.getRack());
        }
    }

    public Session getSession() {
        System.out.println(this.session.getState());
        return this.session;
    }

    public void close() {
        System.out.println("Fechando Conexão!");
        cluster.close();
    }

    public static void main(String[] args) {
        SimpleClient simpleClient = new SimpleClient();
        simpleClient.connect("127.0.0.1");
        simpleClient.close();
    }
}
