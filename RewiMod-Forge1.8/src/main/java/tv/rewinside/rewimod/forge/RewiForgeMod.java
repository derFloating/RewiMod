/*
 * RewiMod, a Minecraft Client Enhancement
 * Copyright (C) rewinside.tv <https://rewinside.tv/>
 * Copyright (C) RewiMod team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tv.rewinside.rewimod.forge;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
import tv.rewinside.rewimod.core.RewiMod;
import tv.rewinside.rewimod.forge.handlers.GlStateManagerHandler;
import tv.rewinside.rewimod.forge.handlers.GuiHandler;
import tv.rewinside.rewimod.forge.handlers.TextureHandler;
import tv.rewinside.rewimod.forge.listener.GuiListener;

@Mod(modid = "%MOD_ID%", name = "%MOD_NAME%", version = "%MOD_VERSION%", canBeDeactivated = RewiMod.DEACTIVATEABLE, certificateFingerprint = RewiMod.FINGERPRINT)
public class RewiForgeMod extends RewiMod {

	@Getter private final TextureHandler textureHandler = new TextureHandler();
	@Getter private final GuiHandler guiHandler = new GuiHandler();
	@Getter private final GlStateManagerHandler glStateManagerHandler = new GlStateManagerHandler();

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.initialize(
				Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode(),
				Minecraft.getMinecraft().getVersion(),
				Minecraft.getMinecraft().getSession().getPlayerID(),
				Minecraft.getMinecraft().getSession().getUsername()
		);
	}

	@Mod.EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		super.onFingerprintViolation();
	}

	@Override
	protected void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new GuiListener());
	}

	@Override
	public void connectToServer(String host, int port) {
		GuiScreen parent = Minecraft.getMinecraft().currentScreen;
		Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(parent, Minecraft.getMinecraft(), host, port));
	}

	@Override
	public String getModId() {
		return "%MOD_ID%";
	}

	@Override
	public String getName() {
		return "%MOD_NAME%";
	}

	@Override
	public String getVersion() {
		return "%MOD_VERSION%";
	}

	@Override
	public void setDisplayTitle(String displayTitle) {
		Display.setTitle(displayTitle);
	}

}
