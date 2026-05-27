<div align="center">

# ❄️ WntrWgItems

🛡️ **Disable Specific Item Interactions Inside WorldGuard Regions Seamlessly** 🛡️

[![Platform](https://img.shields.io/badge/Platform-Paper%20%7C%20Folia%20%7C%20Spigot-00bcd4?style=for-the-badge&logo=minecraft)](https://papermc.io)
[![API Version](https://img.shields.io/badge/API-1.21-FF5722?style=for-the-badge)](https://papermc.io)
[![Folia Compatible](https://img.shields.io/badge/Folia-Compatible-4CAF50?style=for-the-badge)](https://github.com/PaperMC/Folia)
[![Discord Support](https://img.shields.io/badge/Discord-ashyyhere-7289da?style=for-the-badge&logo=discord)](https://discord.com)

---

**WntrWgItems** is a high-performance, lightweight, and production-ready Minecraft plugin designed to disable specific overpowered item mechanics within defined WorldGuard regions. Engineered from the ground up for high-capacity servers, it runs completely thread-safe on both standard Paper servers and multi-threaded regional **Folia** environments.

</div>

---

## ✨ Features

- ⚡ **Ultra High Performance:** Light-weight listeners with fast WorldGuard region queries.
- 🧵 **Folia Compatible:** Built with thread-safe execution principles—no dangerous global scheduler tasks or cross-region access.
- 🚫 **Comprehensive Item Blocks:** Covers 9 primary game-disrupting item interaction types out of the box.
- 💬 **ActionBar Notifications:** Sends customizable, color-coded notices directly to the action bar to keep server chat clean.
- 🔄 **Hot Reloading:** Change region lists and messages on the fly with `/wgitems reload`.

---

## 🛡️ Supported Restriction Keys

| YAML Key | Disabled Mechanics |
| :--- | :--- |
| **`Mace-Disable`** | Blocks left/right click usage and smash attacks inside target regions. |
| **`Rockets-Disable`** | Restricts using firework rockets for Elytra boosting and blocks launches. |
| **`Wind-Charge-Disable`** | Blocks launching or right-clicking wind charges. |
| **`Spear-Disable`** | Blocks throwing, left-clicking, and melee hits with Tridents. |
| **`Elytra-Disable`** | Disables glide toggling and actively cancels active glides entering restricted zones. |
| **`Fishing-Rod-Pull-Disable`** | Disables pulling and cancels target rubber-banding momentum instantly. |
| **`End-Crystal-Disable`** | Blocks crystal placement on obsidian/bedrock and prevents crystal detonation. |
| **`Golden-Apple-Disable`** | Restricts consuming regular and enchanted golden apples inside target areas. |
| **`Splash-Potion-Disable`** | Blocks throwing splash/lingering potions and nullifies landing splash effects. |

---

## 📦 Installation

1. Download `Wntr-WgItems.jar` from your builds folder.
2. Drop it into your server's `plugins/` directory.
3. Make sure you have **WorldGuard** installed.
4. Restart your server.

---

## ⚙️ Configuration (`config.yml`)

The layout is clean, minimalist, and highly customizable:

```yaml
Mace-Disable:
  - "spawn"
Rockets-Disable:
  - "spawn"
Wind-Charge-Disable:
  - "spawn"
Spear-Disable:
  - "spawn"
Elytra-Disable:
  - "spawn"
Fishing-Rod-Pull-Disable:
  - "spawn"
End-Crystal-Disable:
  - "spawn"
Golden-Apple-Disable:
  - "spawn"
Splash-Potion-Disable:
  - "spawn"

messages:
  mace-disabled: "&cYou cannot use a Mace here."
  rockets-disabled: "&cFirework rockets are disabled in this area."
  wind-charge-disabled: "&cWind Charges are disabled in this area."
  spear-disabled: "&cTridents are disabled in this area."
  elytra-disabled: "&cElytra gliding is disabled in this area."
  fishing-rod-disabled: "&cFishing-rod pulling is disabled here."
  end-crystal-disabled: "&cEnd Crystals are disabled in this area."
  golden-apple-disabled: "&cGolden Apples are disabled in this area."
  splash-potion-disabled: "&cSplash Potions are disabled in this area."
```

---

## 🔑 Commands & Permissions

- `/wgitems reload` — Reloads the configuration changes dynamically.
  - **Permission:** `wgitems.reload` (Defaults to `op`)

---

## 🛠️ Developer & Support

Developed with ❤️ by **Ashyyy**
- 💬 **Discord:** `ashyyhere`

If you need any custom features, custom forks, or immediate assistance, feel free to reach out on Discord!
