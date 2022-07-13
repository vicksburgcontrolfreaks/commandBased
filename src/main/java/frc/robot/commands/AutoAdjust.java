// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Limelight;
// import frc.robot.Constants.AdjusterConstants;
// import frc.robot.Constants.LimelightConstants;
// import frc.robot.subsystems.Adjuster;

// public class AutoAdjust extends CommandBase {
//     // Creates a new AutoAdjuster. This is the code that keeps the adjuster at the appropriate angle for the current distance
//   private final Adjuster m_adjuster;
//   private final Limelight m_limelight;
//   double adjusterPower;
//   double mode;

//   public AutoAdjust(Adjuster subsystem, Limelight sLimelight) {
//     // defines the adjuster and limelight that are called in
//     m_adjuster = subsystem;
//     m_limelight = sLimelight;
//     addRequirements(m_adjuster, m_limelight);
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     //sets the current power to 0 when this command is scheduled
//     adjusterPower = 0;
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     //calculates the distance that the adjuster needs to move, in degrees, to be at the correct angle
    
//     double targetPosition = -m_adjuster.distanceAdjust();
//     double currentAngle = m_adjuster.adjusterEncoderP()/AdjusterConstants.ticksPerDegree;
//     double targetDistance = targetPosition - currentAngle;
//     double targetDistanceAbs = Math.abs(targetDistance);
//     double targetSign = Math.signum(targetDistance);
//     //equation to convert distance to angle (not yet complete)
//     double distancePower = targetSign*(2*targetDistanceAbs);
    
//     //if the target can be seen, runs the adjuster to the correct position. Otherwise sets it to 0.
//     if(LimelightConstants.visible)
//       m_adjuster.runAdjuster(distancePower);   
//     else 
//       m_adjuster.runAdjuster(0); 
// }
  


  


//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     //if this code is interupted by other adjuster code, stops the adjuster.
//     if(interrupted)
//       m_adjuster.runAdjuster(0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     //This command does not end on its own
//     return false;
//   }
// }