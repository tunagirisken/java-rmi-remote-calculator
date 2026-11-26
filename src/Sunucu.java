import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Sunucu: registry acar ve nesneyi bir isimle kaydeder.
// Kullanim: java Sunucu [sunucu_ip_adresi]
public class Sunucu {

    // Registry ve uzak nesne static alanlarda tutulur; aksi halde
    // main bittikten sonra garbage collector tarafindan toplanabilirler
    // ve istemci NoSuchObjectException alir.
    private static Registry registry;
    private static Toplama servis;

    public static void main(String[] args) {
        try {
            // Farkli makinadan erisim icin sunucunun IP adresi
            if (args.length > 0) {
                System.setProperty("java.rmi.server.hostname", args[0]);
            }

            // 1099 portunda registry (isim rehberi) baslat
            registry = LocateRegistry.createRegistry(1099);

            // Nesneyi olustur ve "ToplamaServisi" adiyla kaydet
            servis = new ToplamaImpl();
            registry.rebind("ToplamaServisi", servis); // registry'ye stub kaydedilir

            System.out.println("Sunucu hazir. 'ToplamaServisi' 1099 portunda bekliyor...");
        } catch (Exception e) {
            System.err.println("Sunucu hatasi: " + e);
            e.printStackTrace();
        }
    }
}
