package ie.gmit.sw.ai;

public class CharacterManager implements Command {
    private int energy = 1;
    private int target = 1;
    private int sword = 1;
    private int gun = 1;

    @Override
    public void execute() {
        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, sword, gun, target);
        if(output == 0) output = output + 1;

        System.out.println("Character Task: " + output);
        System.out.println("Energy Level: " + energy);
        System.out.println("Target: " + target);
        System.out.println("Sword: " + sword);
        System.out.println("Gun: " + gun);

        switch (output) {
            case 1 -> panic();
            case 2 -> attack();
            case 3 -> hide();
            case 4 -> run();
        }
    }

    private void panic() {
        System.out.println("Panic");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }

    private void attack() {
        System.out.println("Attack");
        target = (int) (2 * Math.random());
    }

    private void hide() {
        System.out.println("Hide");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }

    private void run() {
        System.out.println("Run");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }
}
