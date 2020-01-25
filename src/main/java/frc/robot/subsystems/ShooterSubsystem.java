package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private Victor shooterMotor = new Victor(Constants.shooterMotor);
    private Victor aimMotor = new Victor(Constants.aimMotor);

    public ShooterSubsystem() {
        super();
    }

    public void runShooterMotor(double speed) {
        shooterMotor.set(speed);
    }

    public void goToShootPosition() {
        //to do plz
    }

    public void goToPickupPosition() {
        //to do
    }

    public boolean isInShootPosition() {
        return false;
    }
}
