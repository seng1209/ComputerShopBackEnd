import { useState } from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

function DeleteSale({ uuid }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const deleteSale = async () => {
    try {
      await axios.put(`http://localhost:8080/api/v1/sales/${uuid}`);
      window.location.reload();
    } catch (err) {
      console.log(err);
    }
    setShow(false);
  };

  return (
    <>
      <Button variant="danger" onClick={handleShow}>
        Delete
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Delete Sale at UUID : {uuid}</Modal.Title>
        </Modal.Header>
        <Modal.Body>Are you sure to delete?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="danger" onClick={deleteSale}>
            Delete
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default DeleteSale;
