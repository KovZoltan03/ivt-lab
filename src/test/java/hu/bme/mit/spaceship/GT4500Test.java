package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;

  @BeforeEach
  public void init(){
    primaryMock = mock(TorpedoStore.class);
    secondaryMock = mock(TorpedoStore.class);
    
    this.ship = new GT4500(primaryMock, secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_PrimaryTorpedoStore_Empty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(1)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Alternating_BecomesEmpty(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    when(primaryMock.isEmpty()).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);



    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);

  }


  @Test
  public void fireTorpedo_Single_BothTorpedoStore_Empty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_All_BothTorpedoStore_Empty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.ALL);


    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_All_PrimaryTorpedoStore_Empty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.ALL);


    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(1)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Alternating_SecondaryEmpty(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(primaryMock, times(2)).fire(1);
    verify(secondaryMock, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_All_SecondaryEmpty(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(false);
    when(secondaryMock.isEmpty()).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.ALL);


    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);

  }


}

