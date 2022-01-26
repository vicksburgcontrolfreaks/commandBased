package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
public class Collector extends SubsystemBase {
public final CANSparkMax m_collector = new CANSparkMax(5, MotorType.kBrushless);
public double collectB = 1;
public double collectF = .75;


public void stopCollector() {
    m_collector.set(0);
  }

  public void CollectorF() {
    m_collector.set(collectF);
  }

  public void CollectorB() {
    m_collector.set(collectB);
  }

}
