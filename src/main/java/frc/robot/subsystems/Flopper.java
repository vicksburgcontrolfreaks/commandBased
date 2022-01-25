// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flopper extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final DoubleSolenoid flopperL = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  private final DoubleSolenoid flopperR = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void flopUp(){
    flopperL.set(Value.kForward);
    flopperR.set(Value.kForward);
  }

  public void flopDown(){
    flopperL.set(Value.kReverse);
    flopperR.set(Value.kReverse);
  }

  public void flopOff(){
    flopperL.set(Value.kOff);
    flopperR.set(Value.kOff);
  }
}
