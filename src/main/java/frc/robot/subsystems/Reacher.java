// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Reacher extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final DoubleSolenoid reacherL = new DoubleSolenoid(PneumaticsModuleType.REVPH, 4, 5);
  private final DoubleSolenoid reacherR = new DoubleSolenoid(PneumaticsModuleType.REVPH, 6, 7);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void reachUp(){
    reacherL.set(Value.kReverse);
    reacherR.set(Value.kReverse);
  }

  public void reachDown(){
    reacherL.set(Value.kForward);
    reacherR.set(Value.kForward);
  }

  public void reachOff(){
    reacherL.set(Value.kOff);
    reacherR.set(Value.kOff);
  }
}