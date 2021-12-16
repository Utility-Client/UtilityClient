# Utility Client 2

![](https://img.shields.io/github/workflow/status/Utility-Client/UtilityClient2/Java%20CI%20with%20Maven?style=for-the-badge) [![](https://img.shields.io/discord/753596597983772802?color=%23f66b70&label=Utility%20Client&style=for-the-badge)](https://uc.gamingcraft.de/discord) ![](https://img.shields.io/github/downloads/Utility-Client/UtilityClient2/total?style=for-the-badge)<br>[Roadmap](https://trello.com/b/KgoKb6pQ/update-roadmap) - [Download](https://github.com/Utility-Client/UtilityClient2/releases)

## Install
- Download the latest release [here](https://github.com/Utility-Client/UtilityClient2/releases).
- Extract the zip into a folder with the **exact** name and move the folder into your Minecraft's `versions` folder.
- Create a profile in your Minecraft Launcher.

## Contribute

### Required Tools
- Git Bash or GitHub Desktop
- JetBrains Intellij IDEA

### How to Contribute

#### Setup Workspace
- Make a fork.
- Clone your fork using GitHub Desktop or the Git Bash.
- Create a `Application` Configuration inside IntelliJ.
  - VM Options: `-Djava.library.path=versions/1.8.8/1.8.8-natives/`
  - Main Class: `net.minecraft.Start`
  - Working directory: `C:\Users\<windows_username>\AppData\Roaming\.minecraft`
- Click `Apply` and `OK and press`.

#### Start client from workspace
Press `SHIFT` + `F10` or press the green Start Button or Bug for debugging.

## Contributer

<table>
<tr>
<th> Developers </th>
<th> Maintainer </th>
<th> Contributer </th>
</tr>
<tr>
<td>
    
<img src="https://avatars.githubusercontent.com/u/49761607?v=4" width="100">

[GamingCraft_hd](http://gamingcraft.de)

</td>
<td>
  There are no maintainer.
</td>
<td>
    
<img src="https://avatars1.githubusercontent.com/u/63241406?v=4" width="100">

[Niklas-Dev](https://github.com/Niklas-Dev)
</td>
</tr>
</table>

## Applications used
- [BOM Remover](https://www.mannaz.at/codebase/utf-byte-order-mark-bom-remover) by [whlk](https://github.com/whlk)
- [Maven](https://maven.apache.org/) by [Apache](https://www.apache.org/)
- [MavenMCP](https://github.com/Hexeption/MavenMCP) by [Hexeption](https://github.com/Hexeption)
