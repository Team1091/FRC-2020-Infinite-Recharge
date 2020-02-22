package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AmmoCounterSubsystem;


public class TrackAmmoCommand extends CommandBase {
    private final double finalBallTravelTimeRequired = .5;

    private AmmoCounterSubsystem ammoCounterSubsystem;
    private boolean seenPreviously = false;
    private boolean isShooting;
    private Timer timer;
    private boolean timerStarted = false;


    public TrackAmmoCommand(AmmoCounterSubsystem ammoCounterSubsystem, boolean isShooting) {
        this.ammoCounterSubsystem = ammoCounterSubsystem;
        this.isShooting = isShooting;
        timer = new Timer();
        addRequirements(ammoCounterSubsystem);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        SmartDashboard.putString("Track Ammo", isShooting ? "Shooting" : "Loading");
        if (timerStarted) {
            return;
        }

        if (ammoCounterSubsystem.ballSeen()) {
            seenPreviously = true;
            return;
        }
        if (seenPreviously) {
            seenPreviously = false;
            if (isShooting) {
                ammoCounterSubsystem.subtractAmmo();
                return;
            }
            ammoCounterSubsystem.addAmmo();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putString("Track Ammo", "Standing By");
        timer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (isShooting) {
            return ammoCounterSubsystem.outOfAmmo();
        }
        //If picking up balls final ball needs to travel to top of hopper
        if (ammoCounterSubsystem.atMaxCapacity() && !timerStarted) {
            timerStarted = true;
            timer.start();
        }
        return timer.hasPeriodPassed(finalBallTravelTimeRequired);
    }
}
