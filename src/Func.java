import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Func {

    public static String generateGender() {
        Random random = new Random();
        int n = random.nextInt(2) + 1;
        if (n == 1)
            return "ERKAK";
        else
            return "AYOL";
    }

    public static void createFish(ArrayList<Fish> collectionFish) {
        int lastId = collectionFish.size();
        new Thread(() -> {

            Long randFish1 = randomFish(collectionFish);
            Long randFish2 = randomFish(collectionFish);

            assert randFish1 != null;
            assert randFish2 != null;

            if (randFish1.equals(randFish2)) {
                System.out.println("   Baliqlar uchrashmadi   ");
                return;
            }


            int fishId1 = randFish1.intValue();
            int fishId2 = randFish2.intValue();

            Fish fish1 = new Fish();
            Fish fish2 = new Fish();

            for (Fish fish : collectionFish) {
                if (fish.getId() == fishId1) {
                    fish1 = fish;
                }
                if (fish.getId() == fishId2) {
                    fish2 = fish;
                }

            }


            if (!(fish1.getGender().equals(fish2.getGender()))) {
                Fish fish = new Fish();
                fish.setId(lastId);
                fish.setGender();
                fish.setBornTime();
                fish.setLifeDuration();
                System.out.println(fish.getId() + "- chi baliq tugildi. Jinsi: " + fish.getGender() + " Yashash Davri: " + fish.getLifeDuration());
                collectionFish.add(fish);
            } else {
                System.out.println("!!! Jinsi bir xil baliqlar !!!");
            }
        }
        ).start();
    }

    public static void killFish(ArrayList<Fish> collectionFish) {
        while (true) {
            try {

                if (collectionFish.size() == 1) {
                    System.out.println("Akvariumda 1 ta " + collectionFish.get(0).getGender() + " baliq qoldi");
                    return;
                }

                if (!collectionFish.isEmpty()) {
                    for (int i = 0; i < collectionFish.size(); i++) {
                        Thread.sleep(1000);
                        Func.createFish(collectionFish);
                        if (Math.abs(LocalTime.now().getSecond() - collectionFish.get(i).getBornTime().getSecond()) > collectionFish.get(i).getLifeDuration()) {
                            System.out.println(collectionFish.get(i).getId() + "- chi baliq oldi. Jinsi: " + collectionFish.get(i).getGender());
                            collectionFish.remove(collectionFish.get(i));
                        }
                    }
                } else {
                    System.out.println("Akvariumda baliq qolmadi");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Long randomFish(ArrayList<Fish> collectionFish) {
        ArrayList<Long> ranFish = new ArrayList<>();
        Random random = new Random();
        for (Fish fish : collectionFish) {
            ranFish.add(fish.getId());
        }
        int ranIndex = random.nextInt(ranFish.size());
        return ranFish.get(ranIndex);
    }

}
