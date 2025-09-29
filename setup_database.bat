@echo off
echo ========================================
echo    Configuration de la Base de Donnees
echo ========================================
echo.
echo Ce script va creer la base de donnees et l'utilisateur MySQL
echo.
echo ATTENTION: Vous devez avoir MySQL installe et accessible
echo avec les droits d'administrateur.
echo.
echo Entrez le mot de passe root MySQL:
mysql -u root -p < database_setup.sql
echo.
echo Configuration terminee!
echo.
echo Vous pouvez maintenant lancer l'application avec start.bat
echo.
pause
