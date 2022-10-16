package my.microservice.videogames.application.queries.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

@Document(collection = "backups")
public class BackupQuery {
    private Date backupOn = Date.from(Instant.now());

    public Date getBackupOn() {
        return backupOn;
    }

    public void setBackupOn(Date backupOn) {
        this.backupOn = backupOn;
    }
}
