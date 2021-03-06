package vswe.stevescarts.guis.buttons;

import net.minecraft.entity.player.EntityPlayer;
import vswe.stevescarts.computer.ComputerTask;
import vswe.stevescarts.modules.workers.ModuleComputer;

public abstract class ButtonVarInteger extends ButtonVar {
	private int dif;

	public ButtonVarInteger(final ModuleComputer module, final LOCATION loc, final int dif) {
		super(module, loc);
		this.dif = dif;
	}

	@Override
	public String toString() {
		if (dif < 0) {
			return "Decrease " + getName() + " by " + -1 * dif;
		}
		return "Increase " + getName() + " by " + dif;
	}

	@Override
	public int texture() {
		if (dif == 1) {
			return 40;
		}
		if (dif == -1) {
			return 41;
		}
		if (dif == 10) {
			return 42;
		}
		if (dif == -10) {
			return 43;
		}
		return super.texture();
	}

	@Override
	public boolean isVisible() {
		if (((ModuleComputer) module).getSelectedTasks() != null) {
			for (final ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
				if (isVarVisible(task)) {
					return false;
				}
			}
		}
		return super.isVisible();
	}

	@Override
	public boolean isEnabled() {
		for (final ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
			if (-128 <= getInteger(task) + dif && getInteger(task) + dif <= 127) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onServerClick(final EntityPlayer player, final int mousebutton, final boolean ctrlKey, final boolean shiftKey) {
		for (final ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
			setInteger(task, getInteger(task) + dif);
		}
	}

	protected abstract String getName();

	protected abstract boolean isVarVisible(final ComputerTask p0);

	protected abstract int getInteger(final ComputerTask p0);

	protected abstract void setInteger(final ComputerTask p0, final int p1);
}
