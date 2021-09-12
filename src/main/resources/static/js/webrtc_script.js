//const Peer = window.Peer;

async function main() {

  // peer is set at peer
  //var peer = window.opener.getPeer();

  const localStream = await navigator.mediaDevices
  .getUserMedia({
    audio: true,
    video: true,
  }).catch(console.error);

  // Render local stream
  // Register caller handler
  localVideo.muted = true;
  localVideo.srcObject = localStream;

  console.log('local video play');
  localVideo.playsInline = true;
  await localVideo.play().catch(console.error);

  //
  // Register handler for Call
  //
  callTrigger.addEventListener('click', () => {
    // Note that you need to ensure the peer has connected to signaling server
    // before using methods of peer instance.
    if (!peer.open) {
      console.log('peer close');
      return;
    }

    const mediaConnection = peer.call(remoteId.value, localStream);

    mediaConnection.on('stream', async stream => {
      // Render remote stream for caller
      console.log('remote strema ' + stream.active )
      remoteVideo.srcObject = stream;
      remoteVideo.playsInline = true;

      console.log('remote video play on click');
      await remoteVideo.play().catch(console.error);
      console.log('remote video played on click');
    });

    mediaConnection.once('close', () => {
        console.log('close request get');
      remoteVideo.srcObject.getTracks().forEach(track => track.stop());
      remoteVideo.srcObject = null;
      localVideo.pause();
    });

    closeTrigger.addEventListener('click', () => {
    	    console.log('close button clicked');
    		mediaConnection.close(true);
    		DATACONNECTION.close(true);
    		remoteId.value='';
    	});
  });

  //
  // Register handler for Receive
  //
  peer.on('call', mediaConnection => {
    mediaConnection.answer(localStream);

    mediaConnection.on('stream', async stream => {
      // Render remote stream for callee
      remoteVideo.srcObject = stream;
      remoteVideo.playsInline = true;

      console.log('remote video play on call');
      await remoteVideo.play().catch(console.error);
      console.log('remote video played on call');
    });

    mediaConnection.once('close', () => {
       console.log('close request get');
      remoteVideo.srcObject.getTracks().forEach(track => track.stop());
      remoteVideo.srcObject = null;
      localVideo.pause();
    });

    closeTrigger.addEventListener('click', () => {
	    	console.log('close button clicked');
    		mediaConnection.close(true);
    		DATACONNECTION.close(true);
    		remoteId.value='';
    	});
  });

};
