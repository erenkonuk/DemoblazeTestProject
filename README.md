DemoBlaze Test Otomasyon Projesi
Bu proje, DemoBlaze web sitesinin işlevselliğini ve güvenilirliğini test etmek için otomasyon testleri içerir. Testler, JUnit, TestNG ve Selenium WebDriver kullanılarak yazılmıştır ve çeşitli kullanıcı senaryolarını kapsamaktadır.

🚀 Kullanılan Teknolojiler
JUnit: Test senaryolarını yazmak ve çalıştırmak için.
TestNG: Testlerin yönetimi ve raporlaması için.
Selenium WebDriver: Tarayıcı otomasyonu sağlamak için.
ExtentReports: Test sonuçlarının raporlanması için.
📂 Proje Yapısı
bash
Kodu kopyala
DemoBlazeTestProject/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.example.utilities/
│   │   │       └── DriverSetup.java  # WebDriver yapılandırma sınıfı
│   │   └── resources/
│   ├── test/
│   │   ├── java/
│   │   │   └── tests/
│   │   │       ├── AboutUsTests.java         # 'About Us' popup testleri
│   │   │       ├── CartPageTests.java        # Sepet sayfası testleri
│   │   │       ├── ContactPopUpTests.java    # İletişim popup testleri
│   │   │       ├── HomePageTests.java        # Ana sayfa testleri
│   │   │       ├── LoginPopUpTests.java      # Giriş popup testleri
│   │   │       └── SignUpPopUpTests.java     # Kayıt popup testleri
│   │   └── resources/
│   │       └── testng.xml                    # TestNG yapılandırma dosyası
├── pom.xml                                   # Maven yapılandırma dosyası
📊 Test Senaryoları
1. About Us Testleri
'About Us' popup'ın doğru görüntülenmesi.
Popup içindeki videonun oynatılması.
Popup'ın kapatma butonuyla kapanması.
2. Sepet (Cart) Testleri
'Place Order' popup'ın görüntülenmesi.
Sipariş formunun başarılı bir şekilde gönderilmesi.
Sepetin boş olup olmadığının kontrolü.
3. İletişim (Contact) Testleri
İletişim popup'ının doğru görüntülenmesi.
İletişim formunun alanlarının kontrolü.
İletişim formunun başarılı bir şekilde gönderilmesi.
4. Ana Sayfa (Home Page) Testleri
Ana sayfanın yükleme süresi.
Navbar üzerindeki butonların işlevselliği.
Carousel görsellerinin kontrolü ve kategori seçimi.
5. Giriş (Login) Testleri
Login popup'ının görüntülenmesi.
Giriş formunun başarıyla gönderilmesi.
Login popup'ının kapatılması.
6. Kayıt (Sign Up) Testleri
Sign Up popup'ının görüntülenmesi.
Kayıt formunun başarılı bir şekilde gönderilmesi.
Sign Up popup'ının kapatılması.
🛠 Nasıl Çalıştırılır?
Projeyi klonlayın:
bash
Kodu kopyala
git clone <repository-link>
cd DemoBlazeTestProject
Maven bağımlılıklarını yükleyin:
bash
Kodu kopyala
mvn install
Testleri çalıştırmak için TestNG XML dosyasını kullanın:
bash
Kodu kopyala
mvn test
🤝 Katkıda Bulunma
Her türlü katkıya açığız! Bu projeyi fork'layarak geliştirmeler yapabilir ve pull request ile katkılarınızı sunabilirsiniz.
