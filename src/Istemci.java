import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Istemci: sunucudaki servisi bulur ve topla() metodunu cagirir.
// Kullanim: java Istemci <sunucu_adresi> <sayi1> <sayi2>
public class Istemci {
    public static void main(String[] args) {
        // Komut satirindan sunucu adresi ve sayilari al
        String sunucu = args.length > 0 ? args[0] : "localhost";
        int a, b;
        try {
            a = args.length > 1 ? Integer.parseInt(args[1]) : 5;
            b = args.length > 2 ? Integer.parseInt(args[2]) : 7;
        } catch (NumberFormatException e) {
            System.err.println("Kullanim: java Istemci <sunucu_adresi> <sayi1> <sayi2>");
            System.exit(1);
            return;
        }

        try {
            // Sunucudaki registry'ye baglan
            Registry registry = LocateRegistry.getRegistry(sunucu, 1099);

            // Servisi isimle bul, registry'den stub alinir
            Toplama servis = (Toplama) registry.lookup("ToplamaServisi");

            // Uzak metodu cagir, toplama sunucuda yapilir
            int sonuc = servis.topla(a, b);
            System.out.println("Uzak sunucudan (" + sunucu + ") gelen sonuc: "
                    + a + " + " + b + " = " + sonuc);
        } catch (Exception e) {
            System.err.println("Istemci hatasi: " + e);
            e.printStackTrace();
        }
    }
}
