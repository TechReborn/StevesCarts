package vswe.stevescarts.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vswe.stevescarts.Constants;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class SoundHandler {
	private static final List<SoundEvent> SOUNDS = new ArrayList<>();

	public static final SoundEvent GEAR_SWITCH = reg("gearswitch");
	public static final SoundEvent WIN = reg("win");
	public static final SoundEvent BOOP = reg("boop");
	public static final SoundEvent GAME_OVER = reg("gameover");
	public static final SoundEvent LINES_1 = reg("1lines");
	public static final SoundEvent LINES_2 = reg("2lines");
	public static final SoundEvent LINES_3 = reg("3lines");
	public static final SoundEvent LINES_4 = reg("4lines");
	public static final SoundEvent HIGH_SCORE = reg("highscore");
	public static final SoundEvent HIT = reg("hit");
	public static final SoundEvent CLICK = reg("click");
	public static final SoundEvent BLOB_CLICK = reg("blobclick");
	public static final SoundEvent FLAG_CLICK = reg("flagclick");
	public static final SoundEvent GOOD_JOB = reg("goodjob");

	public SoundHandler() {
	}

	private static SoundEvent reg(String name) {
		ResourceLocation loc = new ResourceLocation(Constants.MOD_ID, name);
		SoundEvent event = new SoundEvent(loc).setRegistryName(loc);

		SOUNDS.add(event);
		return event;
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> register) {
		for (SoundEvent sound : SOUNDS) {
			register.getRegistry().register(sound);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void playSound(SoundEvent event, SoundCategory category, float volume, float pitch) {
		Minecraft.getMinecraft().getSoundHandler().playSound(new PlayerSound(Minecraft.getMinecraft().player, category, event, volume, pitch));
	}

	private static class PlayerSound extends MovingSound {
		private EntityPlayer player;

		protected PlayerSound(final EntityPlayer player, SoundCategory category, SoundEvent event, float volume, float pitch) {
			super(event, category);
			this.player = player;
			this.volume = volume;
			this.pitch = pitch;
			update();
		}

		@Override
		public void update() {
			xPosF = (float) player.posX;
			yPosF = (float) player.posY;
			zPosF = (float) player.posZ;
		}
	}
}
