import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Fish extends Thread {
    private Long id;
    private String gender;
    private LocalTime bornTime;
    private long lifeDuration;


    public Fish() {
    }


    public LocalTime getBornTime() {
        return bornTime;
    }

    public void setBornTime() {
        this.bornTime = LocalTime.now();
    }


    public long getLifeDuration() {
        return lifeDuration;
    }

    public void setLifeDuration() {
        Random random = new Random();
        this.lifeDuration = random.nextInt(25) + 5;
    }

    public long getId() {
        return id;
    }

    public void setId(long size) {
        this.id = size + 1;
    }

    public String getGender() {
        return gender;
    }

    public void setGender() {
        this.gender = Func.generateGender();
    }

    @Override
    public void run() {
        ArrayList<Fish> collectionFish = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(100);
        for (int i = 0; i < count; i++) {
            Fish fish = new Fish();
            fish.setId(collectionFish.size());
            fish.setGender();
            fish.setBornTime();
            fish.setLifeDuration();
            collectionFish.add(fish);
        }
        collectionFish.forEach(fish -> {
            System.out.println(fish.getId() + "- chi baliq tugildi. Jinsi: " + fish.getGender() + " Yashash Davri: " + fish.getLifeDuration());
        });
        System.out.println("\n Dastur boshlandi \n");
        Func.killFish(collectionFish);
    }

}
