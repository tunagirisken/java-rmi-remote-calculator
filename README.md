# Java RMI ile Uzak Toplama Servisi

Java RMI (Remote Method Invocation) kullanarak, istemcinin başka bir makinadaki
nesnenin metodunu yerel bir metot gibi çağırmasını gösteren küçük bir çalışma.
İstemci iki sayı gönderir, toplama işlemi sunucuda yapılır ve sonuç geri döner.

## Dosyalar
| Dosya | Görevi |
|---|---|
| `src/Toplama.java` | Uzak arayüz (`Remote`). `topla(int a, int b)` metodunu tanımlar. |
| `src/ToplamaImpl.java` | Arayüzün sunucu tarafındaki gerçek uygulaması (`UnicastRemoteObject`). |
| `src/Sunucu.java` | 1099 portunda RMI registry başlatır, nesneyi `ToplamaServisi` adıyla kaydeder. |
| `src/Istemci.java` | Uzak makinadaki registry'den servisi bulur ve `topla()` metodunu çağırır. |

## Gereksinimler
- JDK 8 veya üzeri (JDK 21 ile test edildi)

## Derleme
```bash
javac -d out src/*.java
```

## Çalıştırma (aynı makinada)
Terminal 1:
```bash
java -cp out Sunucu
```
Terminal 2:
```bash
java -cp out Istemci localhost 12 30
# Uzak sunucudan (localhost) gelen sonuc: 12 + 30 = 42
```

## Çalıştırma (iki farklı makinada)
Sunucu makinada (IP örn. `192.168.1.20`):
```bash
java -cp out Sunucu 192.168.1.20
```
İstemci makinada (derlenmiş `out` klasörü burada da olmalı):
```bash
java -cp out Istemci 192.168.1.20 12 30
```
> Not: Sunucuda güvenlik duvarı 1099 portuna ve RMI'ın kullandığı dinamik porta izin vermelidir.

## Nasıl çalışır?
1. Sunucu, `ToplamaImpl` nesnesini oluşturur ve registry'ye isimle kaydeder.
2. İstemci, registry'den bu isimle **stub** (vekil nesne) alır.
3. İstemci `servis.topla(a, b)` çağırdığında stub parametreleri ağ üzerinden sunucuya gönderir (marshalling).
4. Toplama işlemi **sunucu makinada** yapılır, sonuç istemciye geri döner.
