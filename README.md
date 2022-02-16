# GO Securi

Vous retrouverez ici l'application **GO Securi**

Application de visualisation de l’affectation du matériel aux agents de l’entreprise Go Securi

## Commande pour lancer l’appli dans le terminal
- Configurer les fichiers info.properties dans les ressources. Exemples: 
   - templates.path = E:/www/epsi/mspr_tpre500/go-securi-appli/src/main/java/com/formation/epsi/gosecuri/templates/
   - data.dir = E:/www/epsi/mspr_tpre500/go-securi-data/
- Lancer la commande ```npm install``` et ```npm run build``` pour compiler le sass
- Installer les dépendences maven
- Choisir un profil maven (target-dev par default)
- Packager l’application avec maven et exécuter la commande ```java -jar target/gosecuri-1.0-SNAPSHOT.jar```
**Le site est généré dans le dossier public**