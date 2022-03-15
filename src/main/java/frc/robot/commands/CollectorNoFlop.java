// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;

public class CollectorNoFlop extends CommandBase {
  /** Creates a new CollectorFlop. */
  private final Collector m_collector;
  private final Flopper m_flopper;
  private final boolean button;
  private Command collect;


  public CollectorNoFlop(Collector subsystem, Boolean b, Flopper f) {
    m_flopper = f;
    button = b;
    m_collector = subsystem;
    addRequirements(m_flopper);
    addRequirements(m_collector);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    collect = new CollectorRun(m_collector, TestConstants.collectF);
    new FlopIn(m_flopper).execute();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(button)
      collect.schedule();
    else
      collect.cancel();
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
