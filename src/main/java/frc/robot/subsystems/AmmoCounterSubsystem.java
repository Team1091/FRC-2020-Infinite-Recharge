package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class AmmoCounterSubsystem extends SubsystemBase {
    private Rev2mDistanceSensor shotCounter = new Rev2mDistanceSensor(Rev2mDistanceSensor.Port.kOnboard);
    private final double notSeenDistance = 10;
    private final int maxCapacity = 5;
    private int ammoCount = 3;

    public AmmoCounterSubsystem(){
        shotCounter.setAutomaticMode(true);
    }

    public boolean ballSeen(){
        return shotCounter.getRange() < notSeenDistance;
    }

    public void addAmmo(){
        ammoCount++;
    }

    public void subtractAmmo(){
        ammoCount--;
    }

    public boolean atMaxCapcity(){
        return ammoCount >= maxCapacity;
    }

    public boolean outOfAmmo(){
        return ammoCount == 0;
    }
}
