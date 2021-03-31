
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projeto`
--
CREATE DATABASE projeto;
-- --------------------------------------------------------

--
-- Estrutura da tabela `controles`
--

CREATE TABLE `controles` (
  `idProjetos` int(11) NOT NULL,
  `idParticipantes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `participantes`
--

CREATE TABLE `participantes` (
  `idParticipantes` int(11) NOT NULL,
  `nmParticipante` varchar(50) NOT NULL,
  `nmCargo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `projetos`
--

CREATE TABLE `projetos` (
  `idProjetos` int(11) NOT NULL,
  `nmDescricao` varchar(100) NOT NULL,
  `dtInicio` date DEFAULT NULL,
  `dtFinal` date DEFAULT NULL,
  `nrPercConcluido` int(11) DEFAULT NULL,
  `nmSituacao` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `controles`
--
ALTER TABLE `controles`
  ADD PRIMARY KEY (`idProjetos`,`idParticipantes`),
  ADD KEY `participantes_fk_idx` (`idParticipantes`);

ALTER TABLE `participantes`
  ADD PRIMARY KEY (`idParticipantes`);

ALTER TABLE `projetos`
  ADD PRIMARY KEY (`idProjetos`);

ALTER TABLE `participantes`
  MODIFY `idParticipantes` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `projetos`
  MODIFY `idProjetos` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `controles`
  ADD CONSTRAINT `participantes_fk` FOREIGN KEY (`idParticipantes`) REFERENCES `participantes` (`idParticipantes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `projetos_fk` FOREIGN KEY (`idProjetos`) REFERENCES `projetos` (`idProjetos`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
