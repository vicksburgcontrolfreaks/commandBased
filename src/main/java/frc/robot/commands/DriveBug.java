// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
public class DriveBug extends CommandBase {
  /** Creates a new Debug. */
 private final Limelight m_limelight;
 private final MrMills m_mills;
 private final Shooter m_shooter;
 boolean bigRedButton;
  public DriveBug(Limelight l, MrMills m, Shooter s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelight = l;
    m_mills = m;
    m_shooter = s;

    addRequirements(m_mills);
    addRequirements(m_limelight);
    addRequirements(m_shooter);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double absLime = Math.abs(m_limelight.tx());
    if(m_mills.isIndexed() && !m_mills.isOverIndexed() && m_shooter.shooterPrimed() && absLime < 4)
      bigRedButton = true;
    else 
      bigRedButton = false;
    // SmartDashboard.putBoolean("Ready to Fire?", bigRedButton);
    // SmartDashboard.putNumber("limeX", m_limelight.tx());
    // SmartDashboard.putBoolean("limeV", m_limelight.tv());
    // SmartDashboard.putBoolean("Indexed?", m_mills.isIndexed());
    // SmartDashboard.putBoolean("Over Indexed?", !m_mills.isOverIndexed());
    // SmartDashboard.putBoolean("shooter Primed?", m_shooter.shooterPrimed());
    
    





  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
