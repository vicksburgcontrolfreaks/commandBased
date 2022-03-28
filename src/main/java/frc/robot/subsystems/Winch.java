// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Winch extends SubsystemBase {
  //Creates a new Winch. This is the motor that drives the winch to raise the robot up to the hook on the end of the reacher.
  private final CANSparkMax winchL = new CANSparkMax(CANConstants.winchL, MotorType.kBrushless);
  private final CANSparkMax winchR = new CANSparkMax(CANConstants.winchR, MotorType.kBrushless);
  private final RelativeEncoder winchLE = winchL.getEncoder();
  private final RelativeEncoder winchRE = winchR.getEncoder();
  public Winch(){
    // winchLE.setPosition(0);
    // winchRE.setPosition(0);
    winchL.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Winch Position Left", leftWinchP());
    SmartDashboard.putNumber("Winch Position Right", rightWinchP());
  }

  public void winchMove(double speed){
    //sets the speed of the winch to an input value
    winchL.set(speed);
    winchR.set(speed);
  }

  public void lWinchMove(double speed){
    //sets the speed of the winch to an input value
    winchL.set(speed);
  }

  public void rWinchMove(double speed){
    //sets the speed of the winch to an input value
    winchR.set(speed);
  }

  public double leftWinchP(){
    return winchLE.getPosition();
  }

  public double rightWinchP(){
    return winchRE.getPosition();
  }

  public void resetEncoders(){
    winchLE.setPosition(0);
    winchRE.setPosition(0);
  }
}