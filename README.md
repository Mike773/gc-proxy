# Getting Started


### Guides

Как запустить на Ubuntu

- Установите java 21:
    ```
    $ sudo apt install openjdk-21-jdk
    ```
- Установите sdkman:
    ```
  $ curl -s "https://get.sdkman.io" | bash
  $ source "$HOME/.sdkman/bin/sdkman-init.sh"
    ```
  Проверяем версию:
    ```
  $ sdk version
    ```
   Если что-то подобное, то ок
  ```
  SDKMAN!
  script: 5.18.2
  native: 0.4.6
  ```
- Установите Gradle:

  ```
  $ sdk install gradle
  ```
  
- Скачайте исходники:
    ```
  $ apt install git
  $ cd /home
  $ git clone https://github.com/Mike773/gc-proxy.git
    ```
  
- Соберите приложение:
    ```
    $ cd /home/gc-proxy
    $ ./gradlew build 
    ```
- Проверьте JAVA_HOME:
    ```
  echo $JAVA_HOME
    ```

- Добавьте ключи в keystore с доверенными сертами (Убедитесь в корректности $JAVA_HOME. Без нее не сработает):
    ```
  
    openssl s_client -showcerts -connect ngw.devices.sberbank.ru:9443

    openssl s_client -connect ngw.devices.sberbank.ru:9443 | openssl x509 -out ngw_dev_sb_ssl.cert
    
    keytool -import -alias ngw_dev_sb -file ngw_dev_sb_ssl.cert -keystore  $JAVA_HOME/lib/security/cacerts
    
    openssl s_client -showcerts -connect gigachat.devices.sberbank.ru:443
    
    openssl s_client -connect gigachat.devices.sberbank.ru:443 | openssl x509 -out sb_ssl.cert
    
    keytool -import -alias sb_ssl -file sb_ssl.cert -keystore  $JAVA_HOME/lib/security/cacerts
    ```

  
- Запустите приложение (Замените $AUTH на ключ из ЛК):
    
    ```
  $ java -jar build/libs/gc-0.0.1-SNAPSHOT.jar --app.auth=$AUTH
    ```
- Если запустилось, вырубайте с помощью **control-C** 


- Создайте файл **(не забудь заменить $AUTH)**:
    ```
  $ nano /etc/systemd/system/gigachat-proxy.service
  $ chmod 777 /etc/systemd/system/gigachat-proxy.service
    ```
    ```
    [Unit]
    Description=GCProxy
    After=multi-user.target
    [Service]
    Type=simple
    Restart=always
    RestartSec=3
    ExecStart=java -jar /home/gc/build/libs/gc-0.0.1.jar --app.auth=$AUTH
    [Install]
    WantedBy=multi-user.target
    ```
    ```
    sudo systemctl enable gigachat-proxy.service
    sudo systemctl status gigachat-proxy.service
    ```
- Проверяем на 8081 порту извне

