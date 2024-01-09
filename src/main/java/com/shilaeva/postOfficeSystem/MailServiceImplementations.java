package com.shilaeva.postOfficeSystem;

import java.util.logging.*;

public class MailServiceImplementations {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        private final MailService[] mailServices;
        private final MailService realMailService;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            realMailService = new RealMailService();
        }

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService mailService : mailServices) {
                mail = mailService.processMail(mail);
            }

            return realMailService.processMail(mail);
        }
    }

    public static class Spy implements MailService {
        private final Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage mailMessage) {
                if (mailMessage.getFrom().equals(AUSTIN_POWERS) || mailMessage.getTo().equals(AUSTIN_POWERS)) {
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[]{mailMessage.getFrom(), mailMessage.getTo()});
                }
            }

            return mail;
        }
    }

    public static class Thief implements MailService {
        private final int minPrice;
        private int stolenValue;

        public Thief(int maxPrice) {
            this.minPrice = maxPrice;
            stolenValue = 0;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage mailPackage && mailPackage.getContent().getPrice() >= minPrice) {
                stolenValue += mailPackage.getContent().getPrice();


                return new MailPackage(mailPackage.from, mailPackage.to,
                        new Package("stones instead of " + mailPackage.getContent().getContent(), 0));
            }

            return mail;
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage mailPackage) {
                String content = mailPackage.getContent().getContent();
                if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException("Error: illegal package.");
                } else if (content.contains("stones")) {
                    throw new StolenPackageException("Error: package with stones.");
                }
            }

            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {}

        public IllegalPackageException(String message) {
            super(message);
        }

        public IllegalPackageException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {}

        public StolenPackageException(String message) {
            super(message);
        }

        public StolenPackageException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
