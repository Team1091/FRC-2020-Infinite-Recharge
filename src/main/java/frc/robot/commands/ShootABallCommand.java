package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootABallCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private boolean shot;
    private Timer timer;
    private double timeToShoot;



    public ShootABallCommand(ShooterSubsystem shooterSubsystem, double timeToShoot) {
        super();
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        timer = new Timer();
        this.timeToShoot = timeToShoot;
    }

    @Override
    public void initialize() {
        timer.reset();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(timer.hasPeriodPassed(timeToShoot)) {
           shot = true;
           return;
        }
        this.shooterSubsystem.shootshot();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return shot;
    }


}
