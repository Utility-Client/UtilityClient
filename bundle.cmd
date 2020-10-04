cd bundle_with_jar

:: Translatinator
copy /A assets\minecraft\lang\en_US.lang assets\minecraft\lang\en_US.bak
copy /A assets\minecraft\lang\en_GB.lang assets\minecraft\lang\en_GB.bak
copy /A assets\minecraft\lang\ro_RO.lang assets\minecraft\lang\ro_RO.bak
copy /A assets\minecraft\lang\de_DE.lang assets\minecraft\lang\de_DE.bak

pause

echo. >> assets/minecraft/lang/en_US.lang
echo. >> assets/minecraft/lang/en_GB.lang
echo. >> assets/minecraft/lang/ro_RO.lang
echo. >> assets/minecraft/lang/de_DE.lang

curl --url https://raw.githubusercontent.com/Utility-Client/UC-Translations/master/uc/en_US.lang >> assets/minecraft/lang/en_US.lang
curl --url https://raw.githubusercontent.com/Utility-Client/UC-Translations/master/uc/en_GB.lang >> assets/minecraft/lang/en_GB.lang
curl --url https://raw.githubusercontent.com/Utility-Client/UC-Translations/master/uc/ro_RO.lang >> assets/minecraft/lang/ro_RO.lang
curl --url https://raw.githubusercontent.com/Utility-Client/UC-Translations/master/uc/de_DE.lang >> assets/minecraft/lang/de_DE.lang

pause

:: Assetinator
jar uf ../target/UtilityClient.jar *

pause

:: Backupinator
del assets\minecraft\lang\en_US.lang
del assets\minecraft\lang\en_GB.lang
del assets\minecraft\lang\ro_RO.lang
del assets\minecraft\lang\de_DE.lang

pause

move assets\minecraft\lang\en_US.bak assets\minecraft\lang\en_US.lang
move assets\minecraft\lang\en_GB.bak assets\minecraft\lang\en_GB.lang
move assets\minecraft\lang\ro_RO.bak assets\minecraft\lang\ro_RO.lang
move assets\minecraft\lang\de_DE.bak assets\minecraft\lang\de_DE.lang

pause