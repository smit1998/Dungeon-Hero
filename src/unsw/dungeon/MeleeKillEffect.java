
package unsw.dungeon;

public class MeleeKillEffect implements EffectBehaviour {

    private Inventory inventory;
    private Weapon oldWeapon;
    private Weapon meleeKillWeapon;

    public MeleeKillEffect(Dungeon dungeon) {
        meleeKillWeapon = new Weapon(0, 0, dungeon, new InstantKillAttack(), Integer.MAX_VALUE);
        inventory = dungeon.getInventory();
    }

    @Override
    public void startEffect() {
        oldWeapon = inventory.getWeapon();
        inventory.removeItem(oldWeapon);
        inventory.addItem(meleeKillWeapon);
    }

    @Override
    public void stopEffect() {
        inventory.removeItem(meleeKillWeapon);
        inventory.addItem(oldWeapon);
    }

    public boolean addWeapon(Weapon weapon) {
        if (oldWeapon == null) {
            oldWeapon = weapon;
            return true;
        }
        return false;

    }
}