<h1>Urkaz Moon Tools</h1>
<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/urkaz-moon-tools"><img src="https://img.shields.io/curseforge/dt/362825?style=flat-square&logo=curseforge&label=CurseForge&color=f16436" alt="CurseForge download count"></a>
    <a href="https://modrinth.com/mod/urkaz-moon-tools"><img src="https://img.shields.io/modrinth/dt/1sxtqgoT?style=flat-square&logo=modrinth&label=Modrinth&color=5da426" alt="Modrinth download count"></a>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/Minecraft-1.20.6-00AA00?style=flat-square" alt="Minecraft 1.20.6">
    <img src="https://img.shields.io/badge/Fabric-0.16.0-yellow?style=flat-square" alt="Fabric 0.16.0">
    <img src="https://img.shields.io/badge/NeoForge-20.6.119-d7742f?style=flat-square" alt="NeoForge 20.6.119">
    <!img src="https://img.shields.io/badge/Forge-49.1.4-de9e59?style=flat-square" alt="Forge 49.1.4">
</p>
<p>
    This mod adds one item and one block that helps to see and detect the current Moon Phase.
</p>
<h2>New items and blocks</h2>
<table width="100%">
    <tr>
        <td width="20%" align="center">
            <h3>Lunar Clock</h3><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/inv_clock.png">
        </td>
        <td width="50%">
            This clock shows the current Moon phase. Right-clicking with it in the hand will display the name of the phase in the chat.
        </td>
        <td width="30%" align="center">
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/recipe_clock.png"><br><i>Lunar Clock recipe</i>
        </td>
    </tr>
    <tr>
        <td width="20%" align="center" rowspan="2">
            <h3>Lunar Detector</h3><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/inv_sensor.png">
        </td>
        <td width="50%" rowspan="2">
            This block emits a Redstone signal depending on the current Moon phase. The Lunar detector only works during the night, but you can make it work all day in the mod settings.
        </td>
        <td width="30%" align="center">
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/recipe_sensor.png"><br><i>Lunar Detector recipe</i>
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/help_redstone.png" width="257" height="135"><br><i>Redstone signal</i>
        </td>
    </tr>
</table>

<h2>Mod compatibility</h2>

<table width="100%">
    <tr>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/enhanced-celestials.png" width="100" height="100"></td>
        <td width="20%" align="center">
            <b>Enhanced Celestials</b><br>(1.16.5 - 1.20.1)
        </td>
        <td width="50%" rowspan="3">
            The moon icon in the Lunar Clock will be tinted of the same color as the Moon.<br><br>In the mod settings, the strength of the Lunar Detector can be set to emit 9 Redstone units during any event.
        </td>
    </tr>
    <tr>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/lunar.png" width="100" height="100"></td>
        <td width="20%" align="center">
            <b>Lunar</b><br>(1.19.2 & 1.20.1)
        </td>
    </tr>
    <tr>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/crimson_moon.png" width="100" height="100"></td>
        <td width="20%" align="center">
            <b>Crimson Moon</b><br>(1.19 - 1.19.1)
        </td>
    </tr>
    <tr>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/bloodmoon.png" width="100" height="100"></td>
        <td width="20%" align="center">
            <b>Bloodmoon</b><br>(1.12.2)
        </td>
        <td width="50%" rowspan="2">The moon icon in the Lunar Clock will become red during a Bloodmoon event or blue during the Harvestmoon.
            <ul>
                <li>
                    <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_110.png"> If both events appear simultaneously, the clock will display both at the same time (this may happen when playing with Both mods at the same time).
                </li>
            </ul>
            In the mod settings, the strength of the Lunar Detector can be set to emit extra Redstone during Harvestmoon and Bloodmoon:
            <ul>
                <li>
                    <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"> Bloodmoon: 9
                </li>
                <li>
                    <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_100.png"> Harvestmoon: 10
                </li>
            </ul>
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/nyx.png" width="100" height="100">
        </td>
        <td width="20%" align="center">
            <b>Nyx</b><br>(1.12.2)
        </td>
    </tr>
</table>

<h2>Dependencies</h2>

"Urkaz Moon Tools" requires the following mods to work:

<table width="100%">
    <tr>
        <td width="33%" align="center">1.20.1 - 1.20.6<br>Required</td>
        <td width="33%" align="center">1.20.1 - 1.20.6<br>Optional</td>
        <td width="33%" align="center">1.18.3 - 1.19.4<br>Required</td>
    </tr>
    <tr>
        <td width="33%" align="center">Architectury API<br>
                <a href="https://www.curseforge.com/minecraft/mc-mods/architectury-api"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/architectury.png" width="100" height="100"></a>
        </td>
        <td width="33%" align="center">Cloth Config API<br>
                <a href="https://www.curseforge.com/minecraft/mc-mods/cloth-config"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/cloth-config.png" width="100" height="100"></a>
        </td>
        <td width="33%" align="center">Cloth Config API<br>
                <a href="https://www.curseforge.com/minecraft/mc-mods/cloth-config"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/cloth-config.png" width="100" height="100"></a>
        </td>
    </tr>
</table>

<h2>Compatibility table</h2>

<ul>
  <li>❗ = Required to work</li>
  <li>❔ = Optional</li>
  <li><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"> = Mod compatibility</li>
  <li><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_110.png"> = Legacy compatibility</li>
</ul>  

<table width="100%">
    <tr>
        <td align="center"></td>
        <td align="center">1.12.2</td>
        <td align="center">1.16.5</td>
        <td align="center">1.18.2</td>
        <td align="center">1.19</td>
        <td align="center">1.19.1</td>
        <td align="center">1.19.2</td>
        <td align="center">1.19.3</td>
        <td align="center">1.19.4</td>
        <td align="center">1.20.1</td>
        <td align="center">1.20.2</td>
        <td align="center">1.20.4</td>
        <td align="center">1.20.6</td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/cloth-config.png" width="20" height="20"> Cloth Config API
        </td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❔</td>
        <td align="center">❔</td>
        <td align="center">❔</td>
        <td align="center">❔</td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/architectury.png" width="20" height="20"> Architectury API
        </td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
        <td align="center">❗</td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/enhanced-celestials.png" width="20" height="20"> Enhanced Celestials
        </td>
        <td align="center"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
   <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/lunar.png" width="20" height="20"> Lunar
        </td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/crimson_moon.png" width="20" height="20"> Crimson Moon
        </td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_10.png"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/bloodmoon.png" width="20" height="20"> Bloodmoon
        </td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_110.png"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/master/resources/thirdparty/nyx.png" width="20" height="20"> Nyx
        </td>
        <td align="center"><img src="https://raw.githubusercontent.com/Urkaz/UrkazMoonTools/1.12.2/src/main/resources/assets/urkazmoontools/textures/item/moonclock_110.png"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
</table>
