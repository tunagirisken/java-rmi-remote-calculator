import java.rmi.Remote;
import java.rmi.RemoteException;

// Uzak arayuz: istemci ve sunucu bu dosya uzerinden anlasir.
// extends Remote -> bu metotlar baska bilgisayardan cagrilabilir.
public interface Toplama extends Remote {

    // Govdesi yok, sadece metodun tanimi.
    // RemoteException: ag hatasi olabilir.
    int topla(int a, int b) throws RemoteException;
}
