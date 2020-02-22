package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;


public class AmmoCounterSubsystem extends SubsystemBase {
    private Rev2mDistanceSensor shotCounter = new Rev2mDistanceSensor(Rev2mDistanceSensor.Port.kOnboard);
    private final double notSeenDistance = 8.0;
    private final int maxCapacity = 5;
    private int ammoCount = 3;

    public AmmoCounterSubsystem() {
        shotCounter.setAutomaticMode(true);
    }

    public boolean ballSeen() {
        return shotCounter.getRange() < notSeenDistance;
    }

    public void addAmmo() {
        ammoCount++;
    }

    public void subtractAmmo() {
        ammoCount--;
    }

    public boolean atMaxCapacity() {
        return ammoCount >= maxCapacity;
    }

    public boolean outOfAmmo() {
        return ammoCount == 0;
    }

    public void setAmmo(int ammo) {
        ammoCount = MathUtil.clamp(ammo, 0, maxCapacity);
    }

    public int getAmmo() {
        return ammoCount;
    }

    public void reset() {
        ammoCount = 0;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Ammo Count", ammoCount);
        SmartDashboard.putNumber("Lidar Dist", shotCounter.getRange());
    }
}
