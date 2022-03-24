// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  // Creates a new Shooter. This is the set of motors that are used to launch cargo into the hub.
  private final CANSparkMax shooterL = new CANSparkMax(CANConstants.shooterL, MotorType.kBrushless);
  private final CANSparkMax shooterR = new CANSparkMax(CANConstants.shooterR, MotorType.kBrushless);
  private final RelativeEncoder shooterE = shooterR.getEncoder();
  private final MotorControllerGroup shooter = new MotorControllerGroup(shooterL, shooterR);
  private final SparkMaxPIDController leftP = shooterL.getPIDController();
  private final SparkMaxPIDController rightP = shooterR.getPIDController();

  public Shooter(){
    shooterR.setInverted(true);
  }

  @Override
  public void periodic() {
    //periodically checks the current shooter speed and if it is at high enough speed to effectivley launch cargo
    shooterSpeed();
    shooterPrimed();
    SmartDashboard.putNumber("shooterSpeed", shooterSpeed());
    SmartDashboard.putBoolean("isPrimed?", shooterPrimed());
  }


  public void shooterMove(double speed){
    //sets the speed of the shooter to an input value
    shooter.set(speed);
  }

  public double shooterSpeed(){
    //returns the current speed of the shooter
    return -shooterE.getVelocity();
  }

  public void setShootPids(int slot, double kMaxOutput, double kMinOutput){
    leftP.setP(ShooterConstants.shoot_kP, slot);
    leftP.setI(ShooterConstants.shoot_kI, slot);
    leftP.setD(ShooterConstants.shoot_kD, slot);
    leftP.setIZone(ShooterConstants.shoot_kIz, slot);
    leftP.setFF(ShooterConstants.shoot_kFF, slot);
    leftP.setSmartMotionAllowedClosedLoopError(ShooterConstants.shoot_encoderError, slot);
    leftP.setOutputRange(kMinOutput, kMaxOutput, slot);

    rightP.setP(ShooterConstants.shoot_kP, slot);
    rightP.setI(ShooterConstants.shoot_kI, slot);
    rightP.setD(ShooterConstants.shoot_kD, slot);
    rightP.setIZone(ShooterConstants.shoot_kIz, slot);
    rightP.setFF(ShooterConstants.shoot_kFF, slot);
    rightP.setSmartMotionAllowedClosedLoopError(ShooterConstants.shoot_encoderError, slot);
    rightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setSpeed(double speed){
    setShootPids(0, 1, -1);
    rightP.setReference(speed, ControlType.kVelocity, 0);
    leftP.setReference(speed, ControlType.kVelocity, 0);
  }

  public double distanceSpeed(double distance){
    double speed = -(.2715*Math.sin(.0313441*distance + 1.32005) + .7453);
    if(speed <-1)
      return -1;
    else
      return speed;
  }

  public boolean shooterPrimed(){
    //returns whether the shooter has reached the speed needed to launch cargo or not
    if(shooterSpeed() >= ShooterConstants.minSpeed)
      return true;
    else
      return false;
  }
}