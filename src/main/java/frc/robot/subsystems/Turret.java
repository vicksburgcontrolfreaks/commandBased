// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {
  //Creates a new Turret. This is the motor that runs the rotating turret that lets us maintain targeting towards the hub.
  private final CANSparkMax turret = new CANSparkMax(CANConstants.turret, MotorType.kBrushless);
  private final SparkMaxPIDController turretP = turret.getPIDController();
  private final RelativeEncoder turretE = turret.getEncoder();

  public Turret(){   
    turretE.setPosition(0);
  }
  


  public void setTurretPids(int slot, double kMaxOutput, double kMinOutput){
    //establishes the PID values for the turret motors
    turretP.setP(TurretConstants.turret_kP, slot);
    turretP.setI(TurretConstants.turret_kI, slot);
    turretP.setD(TurretConstants.turret_kD, slot);
    turretP.setIZone(TurretConstants.turret_kIz, slot);
    turretP.setFF(TurretConstants.turret_kFF, slot);
    turretP.setSmartMotionAllowedClosedLoopError(TurretConstants.turret_encoderError, slot);
    turretP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setTurret(double distance, double speed, int slot){
    //sets the turret to run to a specific distance based on an input value
    setTurretPids(0, speed, -speed);
    turretP.setReference(distance, ControlType.kPosition, 0);
  }

  public void runTurret(double speed){
    //runs the turret at a set speed
    turret.set(speed);
  }

  @Override
  public void periodic() {
    //periodically checks the current position and speed of the encoder
    turretEncoderP();
    turretEncoderV();
    // SmartDashboard.putNumber("TurretPosition", turretEncoderP()/TurretConstants.ticksPerDegree);
    // SmartDashboard.putNumber("TurretSpeed", turretEncoderV());
  }

  public double turretEncoderP(){
    //returns the current position of the turret
    return turretE.getPosition();
  }

  public double turretEncoderV(){
    //returns the current speed of the turret
    return turretE.getVelocity();
  }

  public void turretReset(){
    turretE.setPosition(0);
  }

}