package com;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/conto")
public class ContoCorrentRest {

	private static ArrayList<Movimento> movimenti = new ArrayList<>();
	private static ArrayList<ContoCorrente> conti = new ArrayList<>();

	////////////////// GET ALL/////////////////////////

	@GET
	@Path("/movimenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimento> getAllMovimenti() {

		return movimenti;
	}

	@GET
	@Path("/conti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAllConti() {

		return conti;

	}

	////////////////////////// CREATE, UPDATE E DELETE CONTO//////
	@POST
	@Path("/creaconto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creaConto(ContoCorrente cc) {
		conti.add(cc);
		return Response.status(201).entity("Conto creato con successo!").build();

	}

	@DELETE
	@Path("/cancellaconto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancellaConto(ContoCorrente cc) {
		for (ContoCorrente con : conti) {
			if (con.getIban().equals(cc.getIban())) {
				conti.remove(con);
				return Response.status(201).entity("Conto rimosso con successo!").build();

			}
		}
		return Response.status(404).entity("Conto non trovato!").build();

	}

	@PUT
	@Path("/aggiornaconto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aggiornaConto(ContoCorrente cc) {
		for (ContoCorrente con : conti) {
			if (con.getIban().equals(cc.getIban())) {
				int index = conti.lastIndexOf(con);
				conti.set(index, cc);
				return Response.status(201).entity("Conto aggiornato con successo!").build();
			}
		}
		return Response.status(404).entity("Aggiornamento FALLITO!").build();
	}

	@PUT
	@Path("/movimento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movimento(Movimento mov) {

		for (ContoCorrente con : conti) {
			if (con.getIban().equals(mov.getIban())) {

				if (mov.getTipo().equals(TipoOperazione.PRELIEVO)) {
					if (mov.getImporto() > con.getSaldo() || mov.getImporto() < 0) {
						return Response.status(404).entity("impossibile prelevare").build();
					}
					double nuovoSaldo = con.getSaldo() - mov.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(mov);
					return Response.status(201)
							.entity("Operazione conclusa con successo! Il nuovo saldo è: " + nuovoSaldo).build();
				}
				if (mov.getTipo().equals(TipoOperazione.VERSAMENTO)) {
					if (mov.getImporto() < 0) {
						return Response.status(404).entity("Operazione non riuscita! Inserire un importo corretto!")
								.build();
					}
					double nuovoSaldo = con.getSaldo() + mov.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(mov);
					return Response.status(201)
							.entity("Operazione conclusa con successo! Il nuovo saldo è: " + nuovoSaldo).build();
				}
			}
		}
		return Response.status(404).entity("operazione FALLITA!").build();
	}
}
