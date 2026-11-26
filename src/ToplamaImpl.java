import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Arayuzun gercek uygulamasi, sunucuda calisir.
// UnicastRemoteObject -> nesne agdan gelen cagrilari dinler.
public class ToplamaImpl extends UnicastRemoteObject implements Toplama {

    public ToplamaImpl() throws RemoteException {
        super(); // nesneyi aga acar, stub burada olusturulur
    }

    @Override
    public int topla(int a, int b) throws RemoteException {
        int sonuc = a + b; // toplama burada yapilir
        System.out.println("Istek geldi: " + a + " + " + b + " = " + sonuc);
        return sonuc; // sonuc istemciye geri gonderilir
    }
}
