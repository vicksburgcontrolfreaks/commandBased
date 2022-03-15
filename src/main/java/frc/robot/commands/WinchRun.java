// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;


// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Flopper;
// import frc.robot.subsystems.Turret;
// import frc.robot.subsystems.Winch;

// public class WinchRun extends CommandBase {
//   private final Winch m_winch;
//   private final Double m_speed;
//   private final Flopper m_flopper;
//   private final Turret m_turret;

//   /** Creates a new WinchRun. */
//  public WinchRun(Winch subsystem, Flopper f, Turret t, double s) {
//     /** Creates a new WinchRun. This code runs the Winch at a specific speed*/
//     m_winch = subsystem;
//     m_speed = s;
//     m_flopper = f;
//     m_turret = t;
//     addRequirements(m_winch);
//     addRequirements(m_flopper);
//     addRequirements(m_turret);
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if(m_turret.turretPrimed() && m_flopper.flopPrimed())
//       m_winch.winchMove(m_speed);
//     else
//       m_winch.winchMove(0);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     //stop the winch when it is interrupted
//     m_winch.winchMove(0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     //this code runs continuously until it is interrupted by other winch code
//     return false;
//   }
// }
