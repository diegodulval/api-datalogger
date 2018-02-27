package br.com.habeis.api.services;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Output;
import br.com.habeis.api.domain.Sensor;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    DeviceService deviceService;

    @Autowired
    OutputService outputService;

    @Autowired
    SensorService sensorService;

    public void instantiateTestDatabase() throws ParseException {

        Device device = new Device();
        device.setDescription("Placa numero #1");
        device.setName("Placa #1");
        device.setId(1);

        device = deviceService.create(device);

        Output output1 = new Output();
        output1.setDescription("Saida numero #1");
        output1.setName("Saida #1");
        output1.setId(1);
        output1.setDevice(device);
        output1.setStatus(00);
        outputService.create(output1);

        Output output2 = new Output();
        output2.setDescription("Saida numero #2");
        output2.setName("Saida #2");
        output2.setId(2);
        output2.setStatus(00);
        output2.setDevice(device);
        outputService.create(output2);

        Output output3 = new Output();
        output3.setDescription("Saida numero #3");
        output3.setName("Saida #3");
        output3.setId(3);
        output3.setStatus(00);
        output3.setDevice(device);
        outputService.create(output3);

        Output output4 = new Output();
        output4.setDescription("Saida numero #4");
        output4.setName("Saida #4");
        output4.setId(4);
        output4.setStatus(00);
        output4.setDevice(device);
        outputService.create(output4);

        Output output5 = new Output();
        output5.setDescription("Saida numero #5");
        output5.setName("Saida #5");
        output5.setId(5);
        output5.setStatus(00);
        output5.setDevice(device);
        outputService.create(output5);

        Sensor sensor1 = new Sensor();
        sensor1.setDescription("Sensor numero #1");
        sensor1.setName("Sensor #1");
        sensor1.setId(1);
        sensor1.setDevice(device);
        sensorService.create(sensor1);

        Sensor sensor2 = new Sensor();
        sensor2.setDescription("Sensor numero #2");
        sensor2.setName("Sensor #2");
        sensor2.setId(2);
        sensor2.setDevice(device);
        sensorService.create(sensor2);

        Sensor sensor3 = new Sensor();
        sensor3.setDescription("Sensor numero #3");
        sensor3.setName("Sensor #3");
        sensor3.setId(3);
        sensor3.setDevice(device);
        sensorService.create(sensor3);

        Sensor sensor4 = new Sensor();
        sensor4.setDescription("Sensor numero #4");
        sensor4.setName("Sensor #4");
        sensor4.setId(4);
        sensor4.setDevice(device);
        sensorService.create(sensor4);

        Sensor sensor5 = new Sensor();
        sensor5.setDescription("Sensor numero #5");
        sensor5.setName("Sensor #5");
        sensor5.setId(5);
        sensor5.setDevice(device);
        sensorService.create(sensor5);

        Sensor sensor6 = new Sensor();
        sensor6.setDescription("Sensor numero #6");
        sensor6.setName("Sensor #6");
        sensor6.setId(6);
        sensor6.setDevice(device);
        sensorService.create(sensor6);

        Sensor sensor7 = new Sensor();
        sensor7.setDescription("Sensor numero #7");
        sensor7.setName("Sensor #7");
        sensor7.setId(7);
        sensor7.setDevice(device);
        sensorService.create(sensor7);

        Sensor sensor8 = new Sensor();
        sensor8.setDescription("Sensor numero #8");
        sensor8.setName("Sensor #8");
        sensor8.setId(8);
        sensor8.setDevice(device);
        sensorService.create(sensor8);

        Sensor sensor9 = new Sensor();
        sensor9.setDescription("Sensor numero #9");
        sensor9.setName("Sensor #9");
        sensor9.setId(9);
        sensor9.setDevice(device);
        sensorService.create(sensor9);

        Sensor sensor10 = new Sensor();
        sensor10.setDescription("Sensor numero #10");
        sensor10.setName("Sensor #10");
        sensor10.setId(10);
        sensor10.setDevice(device);
        sensorService.create(sensor10);

    }
}
