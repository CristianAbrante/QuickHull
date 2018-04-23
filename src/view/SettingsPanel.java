package view;

import javax.swing.JPanel;

public class SettingsPanel {
  JPanel contentPanel;

  public SettingsPanel() {
    setContentPanel(new JPanel());
  }

  /**
   * @return the contentPanel
   */
  public JPanel getContentPanel() {
    return contentPanel;
  }

  /**
   * @param contentPanel the contentPanel to set
   */
  private void setContentPanel(JPanel contentPanel) {
    if (contentPanel != null) {
      this.contentPanel = contentPanel;      
    } else {
      throw new NullPointerException("content panel can't be null");
    }
  }
  
  public int getWidth() {
    return getContentPanel().getWidth();
  }
  
  public int getHeight() {
    return getContentPanel().getHeight();
  }
  
  public void repaint() {
    getContentPanel().repaint();
  }
}
