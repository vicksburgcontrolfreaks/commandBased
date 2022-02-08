// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.TestConstants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax shooterL = new CANSparkMax(CANConstants.shooterL, MotorType.kBrushless);
  private final CANSparkMax shooterR = new CANSparkMax(CANConstants.shooterR, MotorType.kBrushless);
  private final RelativeEncoder shooterE = shooterL.getEncoder();
  private final MotorControllerGroup shooter = new MotorControllerGroup(shooterL, shooterR);

  @Override
  public void periodic() {
    shooterSpeed();
    shooterPrimed();
  }


  public void shooterMove(double speed){
    shooterR.setInverted(true);
    shooter.set(speed);
  }

  public double shooterSpeed(){
    return shooterE.getVelocity();
  }

  public boolean shooterPrimed(){
    if(shooterSpeed() >= TestConstants.shooterMin)
      return true;
    else
      return false;
  }

}