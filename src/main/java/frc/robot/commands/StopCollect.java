package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;

public class StopCollect {
private final Collector m_collector;   

public StopCollect(Collector subsystem) {
    m_collector = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public void initialize() {
    m_collector.stopCollector();
  }
  public void execute() {
    m_collector.stopCollector();
  }
}
