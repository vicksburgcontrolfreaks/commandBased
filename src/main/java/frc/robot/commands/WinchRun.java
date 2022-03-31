// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Winch;

public class WinchRun extends CommandBase {
  private final Winch m_winch;
  private final DoubleSupplier m_x;
  private final DoubleSupplier m_y;

  /** Creates a new WinchRun. */
 public WinchRun(Winch subsystem, DoubleSupplier x, DoubleSupplier y) {
    /** Creates a new WinchRun. This code runs the Winch at a specific speed*/
    m_winch = subsystem;
    m_x = x;
    m_y = y;
    addRequirements(m_winch);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(m_x.getAsDouble() > 0){
        if(m_winch.rightWinchP() < ClimberConstants.autoDistance)
          m_winch.rWinchMove(1);
        else
          m_winch.rWinchMove(0);
        if(m_winch.leftWinchP() < ClimberConstants.autoDistance)
          m_winch.lWinchMove(1);
        else
          m_winch.lWinchMove(0);
      }

      else if(m_y.getAsDouble() > 0){
          if(m_winch.rightWinchP() > 6)
            m_winch.rWinchMove(-1);
          else
            m_winch.rWinchMove(0);
          if(m_winch.leftWinchP() > 6)
            m_winch.lWinchMove(-1);
          else
            m_winch.lWinchMove(0);
      }
      else{ 
        m_winch.lWinchMove(0);
        m_winch.rWinchMove(0);}
    
    // if(m_turret.turretPrimed() && m_winch.rightWinchP() > -5 && m_winch.leftWinchP() > -5)
    //   m_winch.winchMove(m_speed);
    // else
    //   m_winch.winchMove(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stop the winch when it is interrupted
    m_winch.winchMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //this code runs continuously until it is interrupted by other winch code
    return false;
  }
}
